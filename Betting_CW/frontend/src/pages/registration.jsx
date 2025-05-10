import Form from "@/sections/Form";

export const metadata = {
    title: 'Registration',
    isLayoutsHidden: true,
}

export default function () {
    return (
        <Form
            formTitle="Register"
            formDescription="Create your account"
            formLink={
                <a className="login-form__register-link" href="/">
                    I have an account / Sign in
                </a>
            }
            buttonLabel="Register"
        />
    )
}
