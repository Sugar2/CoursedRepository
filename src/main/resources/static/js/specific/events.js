/**
 * Created by Trach on 12/10/2016.
 */
$(document).ready(init);

function init(){
    var titles = ['id', 'Дата початку', 'Дата завершення', 'Тип'];
    insertTable(titles, "content-table");

    fillTable();
}
function fillTable(){
    var entityParams = ['id', 'beginDate', 'expirationDate', 'eventType'];
    fillTableFrom("content-table", API + "/years/getAll", entityParams);
    // var requestAddress = API + "/events/getAll";
    // $.getJSON(requestAddress, function(response){
    //     //Go through the each entity in the response
    //     $.each(response, function (i, entity) {
    //         var htmlRow = "<tr>";
    //         //Go through the each parameter in the entity
    //         $.each(entity, function (paramName, paramValue) {
    //             //If a parameter belongs to params array(argument of function)
    //             if($.inArray(paramName, entityParams) !== -1) {
    //                 console.log("paramName: " + paramName);
    //                 console.log("paramValue: " + paramValue);
    //                 if(paramName.indexOf("Date") !== 1) {
    //                     paramValue = new Date(paramValue);
    //                 }
    //                 //Then we add the value of this parameter to the row
    //                 htmlRow += ("<td>" + paramValue + "</td>");
    //             }
    //         });
    //         htmlRow += "</tr>";
    //         $("#content-table> tbody").append(htmlRow);
    //     });
    // });
}