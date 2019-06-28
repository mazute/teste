$(document).ready(function(){
	$(#buscarEstado).submit(function(event){
		event.preventDefault();
		fireAjaxSubmit();
	});
});

function fireAjaxSubmit(){
	var buscar = {}
	buscar["nome"] = $("#nome").val();
	$("#btnBuscar").prop("disabled", true);
	
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/buscarEstado",
        data: JSON.stringify(buscar),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<pre>"
            + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btnBuscar").prop("disabled", false);

        }
    });
	
}
