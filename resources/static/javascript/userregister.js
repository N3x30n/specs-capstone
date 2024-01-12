const registerForm = document.getElementById('register-form')
const registerUsername = document.getElementById('register-username')
const registerPassword = document.getElementById('register-password')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8080/api/v1/users'

async function addUser(obj) {
    try {
        const response = await fetch(`${baseUrl}/register`, {
            method: 'POST',
            body: JSON.stringify(obj),
            headers: headers
        });

        const responseData = await response.json();

        if (response.status === 200) {
            window.location.replace(responseData[0]);
        } else if (response.status === 500) {
            const errorMessage = responseData[0];
            alert("Username already exists");
        } else {
            console.error('Error:', responseData);
        }
    } catch (error) {
        console.error('Error:', error.message);
    }
}

const handleSubmit = async(e) => {
    e.preventDefault()

    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    }

    await addUser(bodyObj)
    registerUsername.value = ''
    registerPassword.value = ''
}



registerForm.addEventListener("submit", handleSubmit)