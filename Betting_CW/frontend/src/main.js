import axios from 'axios'

axios.get('/api/users/1')
    .then((response) => {
        console.log(response.data)
    })