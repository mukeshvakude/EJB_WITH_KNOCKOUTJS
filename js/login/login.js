
var LoginViewModel = function () {

    var self = this;
    self.actionForm = ko.observable("");
    self.password = ko.observable().extend({ required: true });
    self.email = ko.observable().extend({ required: true, email: true });




    self.checkLoginUser = function () {



        userJsonObject = {
            email: self.email(),
            password: self.password()
        }
        addUrl = "http://localhost:8080/RESTAPITODOAPPLICATION/rest/users/login";

        var flag = false;
        $.ajax({
            url: addUrl,
            type: "POST",
            data: JSON.stringify(userJsonObject),
            async: false,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                sessionStorage.setItem("currentUser", JSON.stringify(data));

                if (data.role === "user" && data.activationStatus === true) {
                    self.actionForm("../dashboard/dashboard.html");
                    flag = true;
                } else if (data.role === "admin" && data.activationStatus === true) {
                    self.actionForm("../admin/admin.html");
                    flag = true;
                } else {
                    flag = false;
                    swal("Error", "User Not Activated", "error");
                }

            },
            error: function (result) {
                swal("Error", result.responseText, "error");
                flag = false;
            }
        });

        return flag;
    }

}

ko.applyBindings(new LoginViewModel());