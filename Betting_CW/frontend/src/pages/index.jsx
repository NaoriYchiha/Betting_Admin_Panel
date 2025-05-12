import Form from "@/sections/Form";

export const metadata = {
    title: 'Login',
    isLayoutsHidden: true,
}

export default function () {
  return (
    <>
        <Form
            className="login-form"
            formTitle="Login"
            formDescription="Sign to your account"
            formLink={
                <a className="login-form__register-link" href="/registration">
                    I don’t have account / Register
                </a>
            }
            buttonLabel="Login"
        />
    </>
  )
}
