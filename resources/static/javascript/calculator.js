const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const formulaIn = document.getElementById('formulaInput')
const formulaContainer = document.getElementById('formulaContainer')

const formulaForm = document.getElementById('formulaForm')
const formulaBody = document.getElementById('formula-body')

const updateFormulaBtn = document.getElementById('update-formula-button')

const baseUrl = 'http://localhost:8080/api/v1/formulas'

const headers = {
    "Content-Type": "application/json"
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}
function appendValue(value) {
    var formulaInput = document.getElementById('calculate');
    formulaInput.value += value;
}

function appendDecimal() {
    var formulaInput = document.getElementById('calculate');
    if (formulaInput.value.indexOf('.') === -1) {
        formulaInput.value += '.';
    }
}

function setOperator(operator) {
    var formulaInput = document.getElementById('calculate');
    if (formulaInput.value !== '' && formulaInput.value.slice(-1) !== '.') {
        formulaInput.value += operator;
    }
}

function calculate() {
    var formulaInput = document.getElementById('calculate').value;
    var formulaOutput = document.getElementById('calculateOut');

    var sanitizedFormula = formulaInput.replace(/[a-zA-Z]/g, '()');

    try {
        var result = eval(sanitizedFormula);

        formulaOutput.textContent = 'Result: ' + result;
    } catch (error) {
        formulaOutput.textContent = 'Error: Invalid formula';
    }
}

function clearCalculator() {
    var formulaInput = document.getElementById('calculate');
    var formulaOutput = document.getElementById('calculateOut');

    formulaInput.value = '';
    formulaOutput.textContent = '';
}


async function addFormula(obj) {
    const response = await fetch(`${baseUrl}/user/${userId}`, {
        method: 'POST',
        body:JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if(response.status === 200) {
        return getFormulasByUser(userId)

    }

}
async function getFormulasByUser(userId){
    await fetch(`${baseUrl}/user/${userId}`, {
        method: 'GET',
        headers: headers
    })
        .then(response => response.json())
        .then(data => createFormulaCards(data))
        .catch(err => console.error(err))
}

async function getFormulaById(formulaId){
    await fetch(`${baseUrl}/${formulaId}`, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleDelete(formulaId){
    await fetch(`${baseUrl}/${formulaId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getFormulasByUser(userId)
}

async function handleFormulaEdit(formulaId){
    let bodyObj = {
        id: formulaId,
        formula: formulaBody.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getFormulasByUser(userId)
}
const handleSubmit = async(e) => {
    e.preventDefault()
    let bodyObj = {
        formula: formulaIn.value
    }
    await addFormula(bodyObj)
    formulaIn.value = ''
}
const createFormulaCards = (array) => {
    console.log(array)
    formulaContainer.innerHTML = ''
    array.forEach(obj => {
        let formulaCard = document.createElement("div")
        formulaCard.innerHTML = `
        <div class="card d-flex" style="width: 18rem; height: 18rem;">
            <div class="card-body d-flex flex-column justify-content-between" style="height: auto;">
                <p class="card-text">${obj.formula}</p>
                <div class="d-flex justify-content-between">
                    <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                    <button onclick="getFormulaById(${obj.id})" type="button" class="btn btn-primary"
                    data-bs-toggle="modal" data-bs-target="#formula-edit-modal">Edit</button>
                </div>
            </div>
        </div>
        `
        formulaContainer.append(formulaCard);
    })
}
const populateModal = (obj) => {
    formulaBody.innerText = ''
    formulaBody.innerText = obj.formula
    updateFormulaBtn.setAttribute('data-formula-id', obj.id)
}

getFormulasByUser(userId);

formulaForm.addEventListener('submit', handleSubmit)

updateFormulaBtn.addEventListener("click", (e) =>{
    let formulaId = e.target.getAttribute('data-formula-id')
    handleFormulaEdit(formulaId);
})