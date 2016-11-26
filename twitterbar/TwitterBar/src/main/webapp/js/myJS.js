
$(function () {
    $('.btn').click(function () {
        var value = $(this).attr("id");
        $.cookie("active", value);
        $(".btn").removeClass("active");
        $("#" + value).addClass("active");
        ajaxcall(value);
    });
    var val;
    if (!$.cookie("active")) {
        $.cookie("active", "Comedy");
        $("#Comedy").addClass("active");
        val = "Comedy";
    } else {
        val = $.cookie("active");
        $("#" + val).addClass("active");
    }
    ajaxcall(val);
});
function ajaxcall(value) {
    $.post("Controller", {
        "query": value
    })
            .done(successful)
            .fail(function () {
                alert("hello");  //to be edited
            });
}
function successful(detail) {
    $('#tweets').empty();
    $.each(detail, function (index, value) {
        var myDiv = $('<div>', {
            "class": "col-xs-3 well tweetDiv"
        })
        var inDiv = $('<div>');

        var img = $('<img>', {
            "src": value.imageUrl,
            "alt": value.userName,
            "class": "tweetImage col-xs-6 img-rounded",
        });
        var name = $('<p>', {
            "text": value.userName,
            id: "userName"
        });
        var bottomDiv = $('<div>', {
            class: "bottomDiv"
        });

        var text = $('<p>', {
            "text": value.imageText
        });
//        var anchor = $('<a/>', {
//            id: "id5",
//            name: "link",
//            href: value.link,
//        });
        console.log(value.link);
        bottomDiv.append(text);
        inDiv.append(img);
        inDiv.append(name);
        myDiv.append(inDiv);
        myDiv.append(bottomDiv);
        $('#tweets').append(myDiv).wrap($('<a/>', {
            id: "id5",
            name: "link",
            href: value.link
        }));
    });
}