$(document).ready(function() {
    $('#loader').hide();
    $("#submit").on("click", function() {
        $("#submit").prop("disabled", true);
        var model = $("#model").val();
        var file = $("#image").val();
        var brand = $("#brand").val();
        var transmissionType = $("#transmissionType").val();
        var airConditioning = $("#airConditioning").val();
        var doors = $("#doors").val();
        var seats = $("#seats").val();
        var form = $("#form").serialize();
        var data = new FormData($("#form")[0]);
        data.append('model', model);
        data.append('brand', brand);
        data.append('transmissionType', transmissionType);
        data.append('airConditioning', airConditioning);
        data.append('doors', doors);
        data.append('seats', seats);
        //alert(data);
        $('#loader').show();
        if (model === "" || file === "" || brand === ""
            || transmissionType === "" || airConditioning === ""
            || doors === "" || seats === "") {
            $("#submit").prop("disabled", false);
            $('#loader').hide();
            $("#model").css("border-color", "red");
            $("#image").css("border-color", "red");
            $("#brand").css("border-color", "red");
            $("#transmissionType").css("border-color", "red");
            $("#airConditioning").css("border-color", "red");
            $("#doors").css("border-color", "red");
            $("#seats").css("border-color", "red");
            $("#error_name").html("Please fill the required field.");
            $("#error_file").html("Please fill the required field.");
            $("#error_price").html("Please fill the required field.");
            $("#error_description").html("Please fill the required field.");
        } else {
            $("#model").css("border-color", "");
            $("#image").css("border-color", "");
            $("#brand").css("border-color", "");
            $("#transmissionType").css("border-color", "");
            $("#airConditioning").css("border-color", "");
            $("#doors").css("border-color", "");
            $("#seats").css("border-color", "");
            $('#error_name').css('opacity', 0);
            $('#error_file').css('opacity', 0);
            $('#error_price').css('opacity', 0);
            $('#error_description').css('opacity', 0);
            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                data: data,
                url: "/vehicle/saveVehicleDetails",
                processData: false,
                contentType: false,
                cache: false,
                success: function(data, statusText, xhr) {
                    console.log(xhr.status);
                    if(xhr.status == "200") {
                        $('#loader').hide();
                        $("#form")[0].reset();
                        $('#success').css('display','block');
                        $("#error").text("");
                        $("#success").html("Product Inserted Succsessfully.");
                        $('#success').delay(2000).fadeOut('slow');
                    }
                },
                error: function(e) {
                    $('#loader').hide();
                    $('#error').css('display','block');
                    $("#error").html("Oops! something went wrong.");
                    $('#error').delay(5000).fadeOut('slow');
                    location.reload();
                }
            });
        }
    });
});
