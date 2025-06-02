import axios from "axios";

const rootSelector = '[data-js-login-form]'

class PostLogin {
    selectors = {
        root: rootSelector,
        fieldUsername: '[data-js-form-field-username]',
        fieldPassword: '[data-js-form-field-password]',
        button: '[data-js-form-button]'
    }

    constructor() {
        this.rootElement = document.querySelector(this.selectors.root)
        this.usernameElement = this.rootElement?.querySelector(this.selectors.fieldUsername)
        this.passwordElement = this.rootElement?.querySelector(this.selectors.fieldPassword)
        this.buttonElement = this.rootElement?.querySelector(this.selectors.button)

        this.bindEvents()
    }

    onButtonClick = (event) => {
        event.preventDefault()

        let userFormData = {
            username: this.usernameElement.value,
            password: this.passwordElement.value,
        }

        axios.post(
            'api/auth/login', {
                ...userFormData,
            }
        ).then(response => {
            console.log(response.data)

        })
    }

    bindEvents() {
        this.buttonElement?.addEventListener('click', this.onButtonClick)
    }
}

export default PostLogin