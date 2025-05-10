import './Form.scss'
import Field from "@/components/Field";
import Button from "@/components/Button";

const Form = (props) => {
    const {
        formTitle,
        formDescription,
        formLink,
        buttonLabel,
    } = props


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
        <div className="container container--reletive">
            <form className="form" aria-labelledby={title}>
                <header className="form__header">
                    <h1
                        className="form__title h3"
                        id={title}
                    >
                        { formTitle }
                    </h1>
                    <p className="form__description">
                        { formDescription }
                    </p>
                </header>
                <div className="form__fields">
                    {fieldItems.map((item, index) => (
                        <Field
                            key={index}
                            {...item}
                        />
                    ))}
                </div>
                <div className="form__actions">
                    { formLink }
                    <Button
                        className="form__submit-button"
                        label={buttonLabel}
                        type="submit"
                        mode="logReg"
                    />
                </div>
            </form>
        </div>
    )
}

export default Form