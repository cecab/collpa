Collpa
=======
Collpa is a template code to glue Axel-F with Google Sheets. [Kirill Chernyshov](https://twitter.com/DeLaGuardo/status/1063002155819458560), developer of [ Axel-F](https://github.com/xapix-io/axel-f), twitted the slides of his recent presentation in [Clojure Berlin meetup](https://www.meetup.com/es-ES/Clojure-Berlin/), where he explained that Axel-F is about: "Giving the fun and power of Clojure to non-programmers". One of the things that would make easier for non-programmers to express their business logic is to work on real spreadsheets,like Google Sheets, that is the aim of this project.

Collpa gives you the basic peaces and the instructions to start working fully interactive (like REPL) over Google Sheets and Clojure to help you (programmer) work with non-programmers in obtaining business logic. It uses Axel-F to process excel-syntax formulas and run them on a Clojure Context.

What you receive
----------------
The components are put together to create an scenario in which the non-programmer can write his/her business logic using a Spreadsheets paradigm, like the formulas, and programmers can work 'behind the scenes' making sure that those rules are executable in Clojure (Thanks to [Axcel-F](https://github.com/xapix-io/axel-f)). These are the componentes:

* The UX/UI Layer, a Google SpreadSheet (GS), with sample formulas
* Google Apps Script code integrated to that spreadsheet for implementing the remove evaluation in a Clojure context.
* Clojure code to start a NREPL and evaluate interactively the formulas writen in GS via HTTP request.

How to use it
-------------
* Copy the Google Sheet, which is publicly available in this [link](https://docs.google.com/spreadsheets/d/1cr32JWrjfCDDeASVIVoLEmuD0QGOqspcrDgMRJE6JRo/edit#gid=0).
* Your copy will work with the example formulas, but that is because is connecting to the original Clojure server, which you must change it to your own server.
* Start your own CLJ http/server in any host with an IP public accesible. Remember that Google  Cloud  needs access there.
* Change the Ip/Address of the CLJ server in your GS Copy to point to your server. Its located in menu: "Tools/ScriptEditor" under the script "lib.gs", function 'axel'.
* Verify that all examples are working after your changes.

From this point, you can start to extend the Spreadsheet in different ways based on your "Business Logic" discovery needs, I will suggests some ways that I used during real implementations.

Suggestions to Extend it
-------------------------
Non-programmers understand their data, so any possible analysis or calculee needs that data (at least a sample of it) to be expressed in a clear way. Implements functions to extract real data, remmember that functions in GS are connectors to Clojure code that you can write, then allow non-programmers implement their needs using GS formulas (which is a subset of Excel BTW), every time they found a lack of any function, you can implement it in Clojure and export it, interactively.

Finally, when you are trying to build an information system, it is better to complement what your future users are telling you about the business with REAL DATA, that is where spreadsheet can play a pragamatic role, given that everybody understand the paradigm of excel, you can let non-programmers express their knowledge using language  they already know.


Contributions
-------------
One efective way to contribute is opening Issues about whatever you need from Collpa in your use case. Also report Bugs, or just help to improve it any other way, like detect my typos here :-).

Copyrights
----------
* [transit-js](https://github.com/cognitect/transit-js). Used to transport efficiently the data between the spreadsheets and Clojure.
* [Axcel-F](https://github.com/xapix-io/axel-f). Used to compile/run formulas writen for Excel, but in a Clojure context


** Sigue ***
 -> Wiki pages detallando como se copia la hoja
 -> Con imagenes de como se cambia el IP
 -> Quizas mas detalles del inicio via clj.
 -> Y como se conecta via Emacs.. un video?? bueno, para esta
    ScreenCast!!  Se podria mostrar las funciones basicas
    Y tambien se podria mostrar la integracion con Datomic para
    mostrar datos.
    