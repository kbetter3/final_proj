/**
 * my.header.js
 */

function my_header_home() {
    $.ajax({
        url: 'test',
        success: my_header_success_home
    });
}

function my_header_success_home(json) {
    
//    var j = $.parseJSON(json);
    console.log(json.data);
    $("#my-header-right-container").html(json.data);
}