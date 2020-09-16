function applyToRequest(requestId) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/browse", true);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(
        {
            "id": requestId
        }
    ));
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            alert("Successfully applied to request");
        }
    }
}