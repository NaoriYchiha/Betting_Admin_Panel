import './Field.scss'

const Field = (props) => {
    const {
        label,
        fieldName,
        fieldId,
        placeholder,
        inputType,
    } = props

    return (
        <div className="field">
            <label className="field__label" htmlFor={fieldId}>{label}</label>
            <input
                className="field__input"
                type={inputType}
                id={fieldId}
                name={fieldName}
                placeholder={placeholder}
            />
        </div>
    )
}

export default Field