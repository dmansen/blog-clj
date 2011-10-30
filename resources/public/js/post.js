$(function() {
    var converter = new Showdown.converter();

    $(".markdown").each(function(index, elem) {
        var md = $(elem).html();
        
        $(elem).html(converter.makeHtml(md));
    });

});