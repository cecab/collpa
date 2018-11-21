"use strict"
goog.provide("com.elpug");

/**
* Convert a list of values into a EDN List for
* function execution.
* @param {String} urlpath Full URL to POST the args.
* @param {List<*>} args The list of values to form the
* function invocation. The first is the name of the function
*/
com.elpug.dosym = function (urlpath,args) {
    var lista1 = transit.tagged("list",[]);
    var sym1 = transit.symbol(args[0]);
    lista1.rep.push(sym1);

    var ss = SpreadsheetApp.getActiveSpreadsheet();
    var whouser = SpreadsheetApp.getActive().getId();
    // Add information for autorization.
    var whostr = transit.map();
    whostr.set(transit.keyword("username"),whouser);
    lista1.rep.push(whostr);
    // Add the func. arguments.
    for (var j = 1 ; j < args.length ; j++) {
	lista1.rep.push(args[j]);
    }
    // Send it using transit and select JSON as the
    // encoding format. 
    var twriter = transit.writer("json");
    var ednq = twriter.write(lista1);
    // This value should be changed according to the
    // server config. 
    var options =
	{
            "method" : "post",
            "contentType": "application/edn",
            "payload" : ednq
	};
    var response = UrlFetchApp.fetch(urlpath, options);
    var out = response.getContentText();
    // Convertirlo en transit js.
    var r = transit.reader("json");
    var tValue =  r.read(out);
    return tValue ;
};
goog.exportSymbol('elpug.dosym',com.elpug.dosym);
