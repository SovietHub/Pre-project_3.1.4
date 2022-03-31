function getUserFromForm(form) {
    var user = {
        id: new FormData(form).get('id'),
        name: new FormData(form).get('name'),
        lastname: new FormData(form).get('lastname'),
        age: new FormData(form).get('age'),
        email: new FormData(form).get('email'),
        password: new FormData(form).get('password'),
        role: new FormData(form).get('role')
    }
    return user
}

function getUserById(id) {
    var msg = $.ajax("/api/users/" + id, {
        async: false,
        method: "get",
        dataType: "json"
    }).responseJSON

    var userCharacter = {
        id: msg.id,
        name: msg.name,
        lastname: msg.lastname,
        age: msg.age,
        email: msg.email,
        password: msg.password,
        roles: msg.roles
    }
    return userCharacter
}

function formModalDelete(obj) {
    var id = obj.value
    var user = getUserById(id)
    $('#formModalDeleteId').val(user.id)
    $('#formModalDeleteName').val(user.name)
    $('#formModalDeleteLastname').val(user.lastname)
    $('#formModalDeleteAge').val(user.age)
    $('#formModalDeleteEmail').val(user.email)
    $('#formModalDeletePassword').val(user.password)
}

function formModalEdit(obj) {
    var id = obj.value
    var user = getUserById(id)
    $('#formModalEditId').val(user.id)
    $('#formModalEditName').val(user.name)
    $('#formModalEditLastname').val(user.lastname)
    $('#formModalEditAge').val(user.age)
    $('#formModalEditEmail').val(user.email)
    $('#formModalEditPassword').val(user.password)
}

function allRoles(el) {
    var result = "";
    for (var i = 0; i < el.roles.length; i++) {
        result = result + " "
        result = result + el.roles[i].roleName.replace('ROLE_', "")
    }
    return result
}

function addInformation() {
    $.ajax("/api/info", {
        method: "get",
        dataType: "json",
        success: function (msg) {
            const tbody = $("#users");
            var tr = $("<tr></tr>").addClass("user").appendTo(tbody)
            $("<td></td>").text(msg.id).appendTo(tr);
            $("<td></td>").text(msg.name).appendTo(tr);
            $("<td></td>").text(msg.lastname).appendTo(tr);
            $("<td></td>").text(msg.age).appendTo(tr);
            $("<td></td>").text(msg.email).appendTo(tr);
            $("<td></td>").text(allRoles(msg)).appendTo(tr);
        }
    })
}

function addInformationAdmin() {
    fetch("/api/users")
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            $("#users").html('');
            data.forEach(function (el) {
                var tbody = $('#users')
                var tr = $('<tr></tr>').appendTo(tbody)
                var td
                $('<td></td>').text(el.id).appendTo(tr)
                $('<td></td>').text(el.name).appendTo(tr)
                $('<td></td>').text(el.lastname).appendTo(tr)
                $('<td></td>').text(el.age).appendTo(tr)
                $('<td></td>').text(el.email).appendTo(tr)
                $('<td></td>').text(allRoles(el)).appendTo(tr)
                td = $('<td></td>').appendTo(tr)
                $('<button></button>').text('Edit').attr('onClick', 'openAndFillModalUpdate(this)')
                    .addClass('btn btn-primary')
                    .attr('data-bs-target', '#editModal')
                    .attr('data-bs-toggle', 'modal')
                    .val(el.id).appendTo(td)
                td = $('<td></td>').appendTo(tr)
                $('<button></button>').text('Delete').appendTo(td).attr('onClick', 'openAndFillModalDelete(this)')
                    .addClass('btn btn-danger')
                    .attr('data-bs-target', '#deleteModal')
                    .attr('data-bs-toggle', 'modal')
                    .val(el.id).appendTo(td)
            })
        });
}

editUser.onsubmit = async (e) => {
    e.preventDefault();
    var user = getUserFromForm(editUser);
    console.log(user)
    let response = await fetch('/api/users/', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user),
    });
    $('#updateModal').modal('hide')
    addInformationAdmin()
};

deleteUser.onsubmit = async (e) => {
    e.preventDefault();
    var user = getUserFromForm(deleteUser);
    console.log(user)
    let response = await fetch('/api/users/' + user.id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user),
    });
    $('#deleteModal').modal('hide')
    addInformationAdmin()
};

addUser.onsubmit = async (e) => {
    e.preventDefault();
    var user = getUserFromForm(addUser);
    let response = await fetch('/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user),
    });
    addInformationAdmin()
};