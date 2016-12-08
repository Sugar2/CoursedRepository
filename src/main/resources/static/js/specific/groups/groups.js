/**
 * Created by Hexray on 04.12.2016.
 */

$(document).ready(function () {
    var titles = ['id', 'Назва', 'Тип', 'Рівень', 'Номер курсу'];
    insertTable(titles, "content-table");

    var entityParams = ['id', 'number', 'groupType', 'groupDegree', 'courseNumber'];
    fillTableFrom("content-table", API + "/groups/getAll", entityParams);

    fillSelect("courseNumber", courseNumbers);
    fillSelect("groupType", groupType);
    fillSelect("groupDegree", groupDegree);

});

//It sends serialized
$('#button-group-post').click(function(){
    var form = $('#modal-body-form');
    sendAjaxPost(form, 'api/groups/create', 'add-dialog');
});

$('#add-dialog').on('show.bs.modal', function() {
    fillSelectYear("yearId", API + "/years/getAll");
    fillSelectFrom("specialityId", API + "/specialities/getAll", "fullName");

    var items = "<option value='0'> " + $("#semesterId > option:first").text() + "</option>";
    $("#semesterId").html(items);
});

//ToDo: обнулить значения
$('#add-dialog').on('hidden.bs.modal', function() {
    $('#modal-body-form').each(function(i,elem){
        var id = $(elem[i]).attr( "id");
        $('#' + id).val('0');
    });
});

