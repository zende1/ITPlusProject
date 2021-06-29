/*global requirejs */

// Ensure any request for this webjar brings in jQuery.
requirejs.config({
    shim: {
        'jquery.flot': [ 'webjars!jquery.js', 'webjars!jquery.colorhelpers.js', 'webjars!explorercanvas.js' ]
    }
});
