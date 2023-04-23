function copyToClipboard() {
    var copyText = document.getElementById("short-url");
    copyText.select();
    copyText.setSelectionRange(0, 99999)
    document.execCommand("copy");
    alert("Copied the text: " + copyText.value);
}

document.getElementById("url-form").addEventListener("submit", function(event) {
    event.preventDefault();
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/shorten-url"; // Replace with your own backend URL
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var shortUrl = this.responseText;
            document.getElementById("shortened-url").style.display = "block";
            document.getElementById("short-url").value='crunching numbers , please wait';

            var delayInMilliseconds = 2000; //1 second

            setTimeout(function() {
                document.getElementById("shortened-url").style.display = "block";
                document.getElementById("short-url").value = shortUrl;
            }, delayInMilliseconds);


        }
    };
    var longUrl = document.getElementById("long-url").value;
    var data = JSON.stringify({"longUrl": longUrl});
    xhr.send(data);
});

// document.getElementById("copy-button").addEventListener("click", copyToClipboard);