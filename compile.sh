#!/bin/sh
# You will need the full path to the git working dir of transit-js
# Please, clone it from  https://github.com/cognitect/transit-js
TRANSIT_HOME=$HOME/dev/transit-js
# Compiler call passing extern files as needed to avoid rename function that we
# need in Google Apps Script.
# You have to download the compiler version: closure-compiler-v20161201.jar
# and make the soft-link for compiler.jar
# Like: compiler.jar -> closure-compiler-v20161201.jar

java -jar compiler.jar  --js_output_file dosym-compiled.js --compilation_level ADVANCED_OPTIMIZATIONS  \
     --externs externs.js \
     --externs transit_externs.js \
$TRANSIT_HOME/deps/closure-library/closure/goog/base.js dosym.js
# The minized (cross-compile) file is  dosym-compiled.js . Copy this file into the
# Script editor in Google Spreasheet.
