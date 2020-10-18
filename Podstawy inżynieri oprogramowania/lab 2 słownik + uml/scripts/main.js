function firstLoad() {
    var fileDisplayArea = document.getElementById('polishAboutFileInput');

    function readTextFile(file) {
        var rawFile = new XMLHttpRequest();
        rawFile.open("GET", file, false);
        rawFile.onreadystatechange = function() {
            if (rawFile.readyState === 4) {
                if (rawFile.status === 200 || rawFile.status == 0) {
                    var allText = rawFile.responseText;
                    fileDisplayArea.innerText = allText
                }
            }
        }
        rawFile.send(null);
    }

    readTextFile("../include/opisPL.md");
}

function secondLoad() {
    var fileDisplayArea = document.getElementById('englishAboutFileInput');

    function readTextFile(file) {
        var rawFile = new XMLHttpRequest();
        rawFile.open("GET", file, false);
        rawFile.onreadystatechange = function() {
            if (rawFile.readyState === 4) {
                if (rawFile.status === 200 || rawFile.status == 0) {
                    var allText = rawFile.responseText;
                    fileDisplayArea.innerText = allText
                }
            }
        }
        rawFile.send(null);
    }

    readTextFile("../include/opisENG.md");
}

function Quequed() {
    firstLoad()
    secondLoad()
}

window.onload = Quequed();