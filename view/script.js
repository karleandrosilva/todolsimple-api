// conexão com API

const url = "http://localhost:8080/task/user/1"; // url para utilizar

// função para parar de carregar
function hideLoader() { 
    document.getElementById("loading").style.display = "none"
}

// função para preecher a tabela
function show(tasks) {
  
    // variavel da tabela para poder colocar o cabeçalho 
    let tab = `<thead>
            <th scope="col">#</th>
            <th scope="col">Description</th>
            <th scope="col">Username</th>
            <th scope="col">User Id<th>
        </thead>`;

    // 
    for (let task of tasks) {
        tab += `
            <tr>
                <td scope="row">${task.id}</td>
                <td>${task.description}</td>
                <td>${task.user.username}</td>
                <td>${task.user.id}</td>
            </tr>
        `;
    }
    document.getElementById("tasks").innerHTML = tab;
}

// função (assincrona) principal que vai acessar a API
async function getAPI(url) {
    try {
        const response = await fetch(url, { method: "GET" });
        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.status}`);
        }

        var data = await response.json();
        console.log(data)
        if (response) 
            hideLoader();
        show(data);
        } catch (error) {
            hideLoader();
            document.getElementById("tasks").innerHTML
        }
}

getAPI(url);