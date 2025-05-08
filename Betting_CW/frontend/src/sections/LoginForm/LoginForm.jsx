import './LoginForm.scss'
import Field from "@/components/Field";
import Button from "@/components/Button";

const LoginForm = () => {

    const title = 'Register'

    const fieldItems = [
        {
            label: "USERNAME",
            fieldId: "username",
            fieldName: "username",
            placeholder: "user1234",
            inputType: "text",
        },
        {
            label: "PASSWORD",
            fieldId: "password",
            fieldName: "password",
            placeholder: "Password_1234",
            inputType: "password",
        },
    ]

    return (
        <form className="login-form" aria-labelledby={title}>
            <header className="login-form__header">
                <h1
                    className="login-form__title h3"
                    id={title}
                >
                    Login
                </h1>
                <p className="login-form__description">
                    Sign to your account
                </p>
            </header>
            <div className="login-form__fields">
                {fieldItems.map((item, index) => (
                    <Field
                        key={index}
                        {...item}
                    />
                ))}
            </div>
            <div className="login-form__actions">
                <a className="login-form__register-link" href="/register">
                    I don’t have account / Register
                </a>
                <Button
                    className="login-form__submit-button"
                    label="Login"
                    type="submit"
                    mode="logReg"
                />
            </div>
        </form>
    )
}

export default LoginForm