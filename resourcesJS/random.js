document.querySelectorAll(".project > tr")[0];
document.querySelectorAll(".project > tr")[6];

function appendLib(path)
{
var script= document.createElement('script');
script.type= 'text/javascript';
script.src=path;
document.head.appendChild(script);
}


html2canvas(document.body, {
    onrendered: function (canvas) {
        canvas.toBlob(function(blob) {
            saveAs(blob, "Dashboard.png");
        });
    }
});

html2canvas(document.body, {
    onrendered: function (canvas) {
        canvas.toBlob(function(blob) {

        });
    }
});

(function(jqueryUrl, callback) {
    if (typeof jqueryUrl != 'string') {
        jqueryUrl = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';
    }
    if (typeof jQuery == 'undefined') {
        var script = document.createElement('script');
        var head = document.getElementsByTagName('head')[0];
        var done = false;
        script.onload = script.onreadystatechange = (function() {
            if (!done && (!this.readyState || this.readyState == 'loaded'
                || this.readyState == 'complete')) {
                done = true;
                script.onload = script.onreadystatechange = null;
                head.removeChild(script);
                callback();
            }
        });
        script.src = jqueryUrl;
        head.appendChild(script);
    }
    else {
        callback();
    }
})(arguments[0], arguments[arguments.length - 1]);


var blabla = document.querySelector('body > div.gui-container.hide-comments.hiderulers > div.gui-bottom-container > div.viewport-container > div.viewport-scrollable.inspectlet-sensitive > canvas:nth-child(1)');
html2canvas(blabla, {
    onrendered: function (canvas) {
        canvas.toBlob(function (blob) {
            var a = document.createElement("a");
            document.body.appendChild(a);
            a.style = "display: none";
            a.href = window.URL.createObjectURL(blob);
            a.download = 'canvas';
            a.click();
            document.body.removeChild(a);
        });
    }
});