$(
    window.console.log("hello my_js.js");
    function searchGuid() {
        console.log("search-guid");
        $.post("/api/guid/demo",function (data, status) {
            alert("data: " + data + " ,status: " + status);
        });
    }

    $(#search-guid).submit(function(event) {
        searchGuid();
    });
);