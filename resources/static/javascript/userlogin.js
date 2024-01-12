const loginForm = document.getElementById('login-form')
const loginUsername = document.getElementById('login-username')
const loginPassword = document.getElementById('login-password')

const baseURL = "http://localhost:8080/api/v1/users"

const headers = {
    'Content-Type':'application/json'
}

const handleSubmit = async (e) => {
    console.log(e)
    e.preventDefault()


    let bodyObj = {
        username: loginUsername.value,
        password: loginPassword.value
    }

    const response = await fetch(`${baseURL}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()
    console.log(responseArr)

    if (response.status === 200) {
        document.cookie = `userId=${responseArr[1]}`
        window.location.replace(responseArr[0])
    }
}

loginForm.addEventListener("submit", handleSubmit)
