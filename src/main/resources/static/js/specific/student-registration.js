/**
 * Created by Алена on 01.12.2016.
 */
$("#registration-student").on('click', function () {
    fillSelectYear("yearId", API + "/years/getAll");
    fillSelectFrom("specialityId", API + "/specialities/getAll", "fullName");
    fillSelect("studentEducationStatus", studentEducationStatus)
});

// заполняет группы для выбранных семестра и специальности
function fillGroups(){
    var firstString = $("#groupId > option:first").text();
    var items = "<option value='0'> " + firstString + "</option>";

    if ($("#specialityId > option:selected").attr("value") != '0'
    && $("#semesterId > option:selected").attr("value") != '0') {
        $.getJSON(API + "/groups/getAll/", {specialityId: $("#specialityId > option:selected").attr("value"), semesterId: $("#semesterId > option:selected").attr("value")}, function (response) {
            $.each(response, function (i, entity) {
                var myVar = "";
                if(entity.groupType == 'DISTANCE_FORM') myVar = " (заочна)";
                items += "<option value='" + entity.id + "'>" + entity.number + myVar + "</option>";
            });
            $("#groupId").html(items);
        });
    }
    else
    {
        $("#groupId").html(items);
    }
};

$("#specialityId").on('change', function () {
    fillGroups()
});

$("#semesterId").on('change', function () {
    fillGroups()
});

$("#yearId").on('change', function () {
    var firstString = $("#groupId > option:first").text();
    var items = "<option value='0'> " + firstString + "</option>";
    $("#groupIdId").html(items);
});

// $('#button-student-post').click(function(){
//     var form = $('#registration-student-form');
//     sendAjaxPost(form, '/registration-student', 'Myform');
// });

// $('#button-teacher-post').click(function(){
//     var form = $('#registration-teacher-form');
//     sendAjaxPost(form, '/registration-teacher', 'Myform');
// });
