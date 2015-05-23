(ns assignment5.core
  (:gen-class)
(:require [seesaw.core :as seesaw]
          [seesaw.swingx :as swingx]
          [seesaw.bind :as bind]
          [seesaw.dev :as dev]
          [seesaw.table :as seetable]
          [seesaw.mig :as mig]
          [clj-time.core :as date]))

(eval assignment5.core)

(defn display
 [content width height]
 (let [window (seesaw/frame :title "DVD Movie inventory"
                            :on-close :exit
                            :content content
                            :width width
                            :height height)]
   (seesaw/show! window)))

(def readfile
  "redads file data.clj ehich is dvd inventory"
  (atom (with-open [r (java.io.PushbackReader. (clojure.java.io/reader "data.clj"))]
          (binding [*in* r
                    *read-eval* false]
            (read)))))

(defn writefile [file]
  "writes an atom to file data.clj which is DVD inventory"
  (with-open [w (clojure.java.io/writer "data.clj")]
  (binding [*out* w]
    (pr file))))

(defn writefile-rent [file]
  "writes a file to rent inventory"
  (with-open [w (clojure.java.io/writer "rentmovie.clj")]
  (binding [*out* w]
    (pr file))))

(def rentfile
  "reads a rent file"
  (atom (with-open [r (java.io.PushbackReader. (clojure.java.io/reader "rentmovie.clj"))]
 (binding [*in* r
 *read-eval* false]
 (read)))))

(def buttongroup (seesaw/button-group))

(def tablemovie
  "creats a table which is displayed in movie inventory tab"
  (swingx/table-x
   :id :table-movie
   :horizontal-scroll-enabled? true
   :model [:columns [{:key :id :text "ID"}
                     {:key :name :text "Name"}
                     {:key :price :text "Price"}
                     {:key :quantity :text "Quantity"}]
           :rows @readfile]
   :selection-mode :single))

(def tablerentmovie
  "creats a table which is displayed in rent inventory tab"
  (swingx/table-x
   :id :table-rentmovie
   :horizontal-scroll-enabled? true
   :model [:columns [{:key :id :text "ID"}
                     {:key :name :text "Name"}
                     {:key :person :text "Person"}
                     {:key :DueDate :text "DueDate"}]
           :rows @rentfile
           :selection-mode :single]))

;code to rent a file starts here

(defn removemoviecopy [index]
  "removes a movie copy from movie inventory"
  (swap! readfile update-in [index :quantity] dec)
  (writefile @readfile))

(def buttongroup
  "button group to keep all the buttons in a panel"
  (seesaw/button-group))

(def renter (seesaw/text :text "" :size [10 :by 20] :id :name :tip "Enter name of person" ))

(defn addrentinfo-file [movie]
  "keeps the rent information of a book in file rentmovie.clj"
  (swap! rentfile conj movie)
  (writefile-rent @rentfile))

(defn table-update-quantity [selected-row]
  "updates the movie inventory table my removing a movie copy"
  (seetable/update-at! tablemovie selected-row
                       {:quantity (dec (get (seetable/value-at tablemovie selected-row) :quantity))}))

(defn update-rent-table [new-details]
  "adds a movie details to rent inventory table"
  (seetable/insert-at! tablerentmovie (seetable/row-count tablerentmovie) new-details))

(def rent-widget
  "builds a widget to rent a file"
  (let [message renter
 done (seesaw/button
       :text "rent"
       :group buttongroup
       :listen [:action (fn [e] (let [selected-row (seesaw/selection tablemovie)
                                      quantity     (get (seetable/value-at tablemovie selected-row) :quantity)
                                      id           (get (seetable/value-at tablemovie selected-row) :id)
                                      name-movie   (get (seetable/value-at tablemovie selected-row) :name)
                                      person       (seesaw/text renter)
                                      due-date     (str (date/plus (date/today) (date/weeks 2)))
                                      rent-map     {:id id :name name-movie :person person :DueDate due-date}]
                                  (if (zero? quantity)
                                    (seesaw/alert "No more copies of movie available to rent")
                                    ((removemoviecopy selected-row)
                                     (table-update-quantity selected-row)
                                     (addrentinfo-file rent-map)
                                     (update-rent-table rent-map)))))])]
 (seesaw/top-bottom-split done message)))

;code to update the price of DVD starts here

(def new-price-text (seesaw/text :text "" :size [10 :by 20] :id :newprice :tip "Enter new price" ))

(defn updateprice-file [index newprice]
  "adds new copy to existing movie"
  (swap! readfile assoc-in [index :price] newprice)
  (writefile @readfile))

(defn update-price-table [selected-row newprice]
  "updates the new price in table"
  (seetable/update-at! tablemovie selected-row {:price newprice}))

(def change-price-widget
  (let [message new-price-text
        done (seesaw/button
        :text "change price"
        :group buttongroup
        :listen [:action (fn [e] (let [selected-row (seesaw/selection tablemovie)
                                       new-price (read-string (seesaw/text new-price-text))]
                                   (if (< new-price 0) (seesaw/alert "price should be more than zero")
                                   ((updateprice-file selected-row new-price)
                                   (update-price-table selected-row new-price)))))])]
    (seesaw/top-bottom-split done message)))

;Code fo Find-price of DVD movie starts here

(def find-price-text (seesaw/text :text "" :size [10 :by 20] :id :name :tip "Enter id of movie" ))

(defn find-price [arg]
  "return price of movie with supplied id"
  (let [second-pos 1
        index (dec (read-string (seesaw/text find-price-text)))]
    (str (get (get (find @readfile index) second-pos) :price))))

(def find-price-widget
  (let [message find-price-text
        done (seesaw/button
       :text "findprice"
       :group buttongroup
       :listen [:action (fn [e] (let [price-atom (atom 2)
                                      price-text (seesaw/text)
                                      index (dec (read-string (seesaw/text find-price-text)))
                                      row-count (dec (seetable/row-count tablemovie))]
                                  (if (or (< index 0) (> index row-count))
                                    (seesaw/alert "Supply valid movie ID")
                                    ((bind/bind price-atom (bind/property price-text :text))
                                     (seesaw/show! (seesaw/frame :title "Price Of the DVD Movie"
                                                                 :content price-text
                                                                 :width 20
                                                                 :height 60))
                                     (swap! price-atom find-price)))))])]
    (seesaw/top-bottom-split done message)))


;code to find quantity with supplied ID starts here

(def find-quantity-text (seesaw/text :text "" :size [10 :by 20] :id :namequantity :tip "Enter id of movie" ))

(defn find-quantity [arg]
  (let [second-pos 1
        index (dec (read-string (seesaw/text find-quantity-text)))]
    (str (get (get (find @readfile index) second-pos) :quantity))))


(def find-quantity-widget
  (let [message find-quantity-text
        done (seesaw/button
       :text "findquantity"
       :group buttongroup
       :listen [:action (fn [e] (let [quantity-atom (atom 2)
                                      quantity-text (seesaw/text)
                                      index (dec (read-string (seesaw/text find-quantity-text)))
                                      row-count (dec (seetable/row-count tablemovie))]
                                  (if (or (< index 0) (> index row-count))
                                    (seesaw/alert "Supply valid movie ID")
                                  ((bind/bind quantity-atom (bind/property quantity-text :text))
                                   (seesaw/show! (seesaw/frame :title "Quantity Of the DVD Movie"
                                                               :content quantity-text
                                                               :width 20
                                                               :height 60))
                                  (swap! quantity-atom find-quantity)))))])]
    (seesaw/top-bottom-split done message)))

;code to add new movie starts here

(def mig-panel-add-movies (mig/mig-panel
:border "enter new movie DVD details and press add new movie button"
:constraints ["wrap 2" "[40]10[160]"]
:items [["Name"] [(seesaw/text :text "" :size [200 :by 20] :id :name :tip "Enter book Name")]
        ["Price"] [(seesaw/text :text "" :size [200 :by 20] :id :price :tip "Enter Price for book")]
        [ "Quantity"] [(seesaw/text :text "" :size [200 :by 20] :id :quantity :tip "Enter number of copies of book")]]))

(defn addmovie-file [movie]
  "adds new movie to file"
  (swap! readfile conj movie)
  (writefile @readfile))

(defn insert-table [new-movie]
  (seetable/insert-at! tablemovie (seetable/row-count tablemovie) new-movie))

(def buttonaddnewmovie
  "button to add new movie"
 (seesaw/button
 :text "Add New movie"
  :group buttongroup
 :listen [:action (fn [event] (let [newmoviedetails (seesaw/value mig-panel-add-movies)
                                    id              (inc (seetable/row-count tablemovie))
                                    name-person     (get newmoviedetails :name)
                                    price           (read-string (get newmoviedetails :price))
                                    quantity        (read-string (get newmoviedetails :quantity))
                                    new-movie       {:id id :name name-person :price price :quantity quantity}]

                                (if (some #(= (get newmoviedetails :name) %) (map :name @readfile))
                                (seesaw/alert "movie already exists")
                                  ((addmovie-file new-movie)
                                   (insert-table new-movie)))))]))

;code to add a new movie copy starts here

(defn addmoviecopy [index]
  "adds new copy to existing movie"
  (swap! readfile update-in [index :quantity] inc)
  (writefile @readfile))

(defn update-table-movie [index]
  "updates the table movie inventory with returned DVD"
(seetable/update-at! tablemovie index
                     {:quantity (inc (get (seetable/value-at tablemovie index) :quantity))}))

(def buttonAddCopy
  "button to add new copy of dvd"
  (seesaw/button
   :text "Add New Copy"
   :group buttongroup
   :listen [:action (fn [event] (let [selected-row (seesaw/selection tablemovie)]
                                  (addmoviecopy selected-row)
                                  (update-table-movie selected-row)))]))


;code to return a rented movie starts here

(defn drop-index
  "removes a map in vector"
  [vector-to-drop]
  (let [selected-row (seesaw/selection tablerentmovie)]
  (vec (concat (subvec vector-to-drop 0 selected-row) (subvec vector-to-drop (inc selected-row))))))

(defn update-rent-file []
  "keeps the rent information of a book in file rentmovie.clj"
  (swap! rentfile drop-index)
  (writefile-rent @rentfile))

(defn index-of-returned-movie [selected-row]
  "returns index of the returned file in inventory"
  (dec (get (seetable/value-at tablerentmovie selected-row) :id)))


(def button-return-movie
  "button to return movie"
  (seesaw/button
   :text "return-DVD"
   :listen [:action (fn [event](let [selected-row (seesaw/selection tablerentmovie)
                                     index-movie (index-of-returned-movie selected-row)]
                                 (update-rent-file )
                                 (seetable/remove-at! tablerentmovie selected-row)
                                 (addmoviecopy index-movie)
                                 (update-table-movie index-movie)))]))

(def button-remove
  "removes a movie copy from inventory"
  (seesaw/button
   :text "remove-movie"
   :group buttongroup
   :listen [:action (fn [event](let [selected-row (seesaw/selection tablemovie)
                                     quantity (get (seetable/value-at tablemovie selected-row) :quantity)]
                                 (if (zero? quantity) (seesaw/alert "No more copies of movie available" )
                                 ((removemoviecopy selected-row)
                                 (seetable/update-at!
                                  tablemovie selected-row
                                  {:quantity (dec (get (seetable/value-at tablemovie selected-row) :quantity))})))))]))


;code to construct the GUI

(def panel
  "displays all buttons in a panel"
  (seesaw/vertical-panel :items [(seesaw/label :text "Instructions")
                                 (seesaw/label :text "1. Give ID of movie press find price")
                                 (seesaw/label :text "2. Give ID of movie press find quantity")
                                 (seesaw/label :text "3. Give new price change price")
                                 (seesaw/label :text "4. select a movie, give name of person to rent")
                                 buttonAddCopy find-price-widget find-quantity-widget
                                 change-price-widget rent-widget
                                 button-remove buttonaddnewmovie]))

 (def bordermovie
   "creates a borderpanal for movie inventory tab"
   (seesaw/border-panel
    :east panel
    :south (seesaw/vertical-panel :items [mig-panel-add-movies
                                          buttonaddnewmovie])

    :center (seesaw/scrollable tablemovie)))

 (def borderrentmovie
   "creates a borderpanal for rent inventory tab"
   (seesaw/border-panel
    :center (seesaw/scrollable tablerentmovie)
    :south button-return-movie))

(def order-tab
  "creates a orderpanal for movie inventory,rent inventory tab"
  (seesaw/tabbed-panel
                :placement :top
                :tabs [{:title "DVD movies inventory"
                        :content bordermovie}
                       {:title "Rented Movies"
                        :content borderrentmovie}]))

(defn -main
[& args]
(display order-tab 600 600))
