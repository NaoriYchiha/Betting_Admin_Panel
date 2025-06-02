import './Form.scss'
import Field from "@/components/Field";
import Button from "@/components/Button";
import classNames from "classnames";

const Form = (props) => {
    const {
        className,
        formTitle,
        formDescription,
        formLink,
        buttonLabel,
    } = props


    const title = 'Register'

    const fieldItems = [
        {
            label: "USERNAME",
            inputParams: {
                id: "username",
                name: "username",
                placeholder: "user1234",
                type: "text",
                required: true,
                minLength: 4,
                maxLength: 32,
                ['data-js-form-field-username']: '',
            }
        },
        {
            label: "PASSWORD",
            inputParams: {
                id: "password",
                name: "password",
                placeholder: "Password_1234",
                type: "password",
                minLength: 8,
                maxLength: 20,
                required: true,
                pattern: '^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+$',
                title: "Password must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters",
                ['data-js-form-field-password']: '',
            }
        },
    ]

    return (
        <div className="container container--reletive">
            <form
                className={classNames(className, 'form')}
                aria-labelledby={title}
                {...{[`data-js-${className}`] : ''}}
            >
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
                        dataAtr
                    />
                </div>
            </form>
        </div>
    )
}

export default Form