$(document).ready(function() {
    $("#myBtn").on('click',clickHandler);
});


function clickHandler(event){
    var serializedArr = $("#resolutionForm").serializeArray();
    var serializeObj = {};
    for(var i=0; i <serializedArr.length; i++){
        var currObj = serializedArr[i];
        serializeObj[currObj.name] = currObj.value;
    }
    var formData = JSON.stringify(serializeObj);

    console.log(serializeObj);
    jQuery('#overlay').show();
    //amount_of_toa: "3"
    //authenticity_token: "1XUyc4+vIkNJZvHQ/POPlDasIgH28IpYrNycLMGDZQM="
    //check_goodwill_limit[]: "true"
    //comments: "asf"
    //order_item_id_list: "saf"
    //reason_for_toa[]: "promise_breach"
    //userName: "devarshi.waghela"

    var longUrl = serializeObj.fname;
    $.ajax({
        url:'/getShortUrl/' + longUrl,
        data:formData,
        type:'post',
        contentType:'application/json',
        success: function (msg) {
            var jsonResponse = msg;
            console.log(jsonResponse.short_url);
            var short_url = jsonResponse.short_url;

            document.getElementById('longUrlText').innerHTML = "Paste the url in address bar" + short_url;
        }
    }).done(function(data){
        jQuery('#overlay').fadeOut();
        console.log(data);
    }).fail(function(error){
        jQuery('#overlay').fadeOut();
        console.log(error);
    });

}