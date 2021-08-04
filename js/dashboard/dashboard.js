function ajaxFunction(uri, method, data) {
    
    return $.ajax({

        type: method,
        url: uri,
        dataType: 'json',
        contentType: 'application/json',
        data: data ? JSON.stringify(data) : null

    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert('Error : ' + errorThrown);
        // console.log(textStatus);
        // alert(jqXHR.responseText);
    });
}
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
var TaskViewModel = function () {

    var currentUpdateTaskId;
    var self = this;
    
    self.allTaskId = function(){
        $("#AllTasksTable").show();
        $("#CompletedTasksTable").hide();
        $("#PendingTasksTable").hide();
    }
    self.completedTaskId = function(){
        $("#CompletedTasksTable").show();
        $("#AllTasksTable").hide();
        $("#PendingTasksTable").hide();
    }
    self.pendingTaskId = function(){
        $("#PendingTasksTable").show();
        $("#AllTasksTable").hide();
        $("#CompletedTasksTable").hide();
       

    }
    self.taskName = ko.observable();
    self.taskDescription = ko.observable();
    self.complitionDate = ko.observable();
    self.priority = ko.observable("None");
    self.task_id = ko.observable();
    self.modelTitle = ko.observable("Add Task");
    // Storing the all Users Data
    
    var currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
    
    

    self.activeUser = ko.observable(currentUser.firstName);
    console.log(self.activeUser());
    // Storing active User Data
    self.userAllTasks = ko.observableArray([]);
   
    function getPerticularUserTasks(){
        let addUrl = "http://localhost:8080/RESTAPITODOAPPLICATION/rest/users/"+parseInt(currentUser.user_id)+"/tasks";
        ajaxFunction(addUrl, 'GET').done(function (data) {
           
            self.userAllTasks(data);
          
        });
    }
    
    // Adding task into localStorage and Observable Array
    self.addTask = function(){
        
        let jsonObj = 
        {
            taskName :self.taskName(),
            taskDescription : self.taskDescription(),
            taskCreationDate : formatDate(),
            complitionDate : self.complitionDate(),
            priority: self.priority(),
            taskStatus : false,

        }
        let addUrl = "http://localhost:8080/RESTAPITODOAPPLICATION/rest/users/"+currentUser.user_id+"/tasks";
        ajaxFunction(addUrl, 'POST',jsonObj).done(function (data) {
            // alert("added");
            swal("Task added successfully", "success", "success");
            getPerticularUserTasks();
        });
  
        
    }
    self.detailTask = function(selectedTask){
        self.taskName(selectedTask.taskName);
        self.taskDescription(selectedTask.taskDescription);
        self.complitionDate(selectedTask.complitionDate);
        self.priority(selectedTask.priority);
        self.task_id(selectedTask.task_id); 
        self.modelTitle("Update Task")
        $('#Save').hide();
        $('#Clear').hide();

        $('#Update').show();
       

    }
    // delete task 
    self.deleteTask = function (currentTask) {
        let addUrl = "http://localhost:8080/RESTAPITODOAPPLICATION/rest/users/"+currentUser.user_id+"/tasks/"+currentTask.task_id;
        ajaxFunction(addUrl, 'DELETE').done(function (data) {
              
            console.log(data);
                getPerticularUserTasks();
        });
    }
    // update task
    self.updateTask = function(){
    
        let updatedTask = {
            taskName : self.taskName(),
            taskDescription : self.taskDescription(),
            complitionDate :self.complitionDate(),
            priority : self.priority(),
        }
       
        let addUrl = "http://localhost:8080/RESTAPITODOAPPLICATION/rest/users/"+currentUser.user_id+"/tasks/"+parseInt(self.task_id());
        ajaxFunction(addUrl, 'PUT',updatedTask).done(function (data) {
                getPerticularUserTasks();
        });

       
        $('#Save').show();
        $('#Clear').show();

        $('#Update').hide();
    //     getPerticularUserTasks();
    }
    self.changeStatus = function(currentTask){
    
        console.log(currentTask.task_id);
        let addUrl = "http://localhost:8080/RESTAPITODOAPPLICATION/rest/users/"+currentUser.user_id+"/tasks/"+currentTask.task_id+"/changeStatus";
        ajaxFunction(addUrl, 'PUT',currentTask).done(function (data) {
              
                getPerticularUserTasks();
        });
    }
    getPerticularUserTasks();
   
}

ko.applyBindings(new TaskViewModel());