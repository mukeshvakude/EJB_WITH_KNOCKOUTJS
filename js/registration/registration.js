// function ajaxFunction(uri, method, data) {

//     return $.ajax({

//         type: method,
//         url: uri,
//         async: false,
//         dataType: 'json',
//         contentType: 'application/json',
//         data: data ? JSON.stringify(data) : null

//     }).fail(function (jqXHR, textStatus, errorThrown) {


//         swal(jqXHR.responseText, "Error", "error");


//     });
// }
var imported = document.createElement('script');
imported.src = '/js/common/ajaxFunction.js';
document.head.appendChild(imported);

function formatDate() {
    var d = new Date(),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
}
function convertDate(date) {
    return date;
}
var ViewModel = function () {
    ko.validation.rules['TEXT'] = {
        validator: function (val, params) {
            var regex = /([A-Za-z])$/;
            if (regex.test(val)) {
                return true;
            }
        },
        message: 'Only Alphabets Allowed'
    };

    ko.validation.rules['PASSWORD'] = {
        validator: function (val, params) {
            var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$/;
            if (regex.test(val)) {
                return true;
            }
        },
        message: 'Minimum six characters, at least one lowerCase and one upperCase letter, one number and one special character:'
    };

    ko.validation.registerExtenders();
    var self = this;
    self.actionForm = ko.observable("");

    self.FirstName = ko.observable().extend({ required: true, TEXT: true });
    self.LastName = ko.observable().extend({ required: true, TEXT: true });
    self.Password = ko.observable().extend({ required: true, PASSWORD: true });
    self.Email = ko.observable().extend({ required: true, email: true });

    self.dateOfBirth = ko.observable().extend({ required: true });
    self.GenderList = ko.observableArray(['Male', 'Female']);
    self.Gender = ko.observable().extend({ required: true });

    //Add new User
    self.addNewUser = function addNewUser() {
        // VALIDATE FORM
        var flag = false;
        self.errors = ko.validation.group(this, { deep: false, observable: false });

        if (self.errors().length === 0) {
            convertDate(self.dateOfBirth())

            var custJsonObject = {
                firstName: self.FirstName(),
                lastName: self.LastName(),
                email: self.Email(),
                gender: self.Gender(),
                password: self.Password(),
                dateOfBirth: self.dateOfBirth(),
                role: 'user',
                activationDate: formatDate(),
                activationStatus: false,
                registrationDate: formatDate(),
            };
            addUrl = "http://localhost:8080/RESTAPITODOAPPLICATION/rest/users/register";
            ajaxFunction(addUrl, 'POST', custJsonObject).done(function (data) {
               
                flag = true;
                swal("User Register successfully", "success", "success");
            });

        }
        else {
          
            swal("Error", "Fill the form completely", "error");
            
        }
        return flag;
    }

};

ko.applyBindings(new ViewModel());