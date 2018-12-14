
var url = "http://127.0.0.1:8080/v2";

// 示例 1
var xhr = new XMLHttpRequest();
xhr.open("POST", url+"/pet");
xhr.setRequestHeader("Content-Type", "application/json");
var pet = { id: 0, category: { id: 12, name: 'kai' } };
xhr.send(JSON.stringify(pet));


// 示例 2
var client = new XMLHttpRequest();
client.open("POST", url+"/pet");
var formData = new FormData();
formData.append('username', 'johndoe');
formData.append('id', 123456);
client.send(formData);


// 示例 3
var client = new XMLHttpRequest();
client.open('GET', url+"/pet");
client.setRequestHeader('X-Test', 'one');
client.setRequestHeader('X-Test', 'two');
client.send();


