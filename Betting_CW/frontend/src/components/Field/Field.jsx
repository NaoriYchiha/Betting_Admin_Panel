import './Field.scss'

const Field = (props) => {
    const {
        label,
        inputParams = {},
    } = props

    return (
        <div className="field">
            <label className="field__label" htmlFor={inputParams.id}>{label}</label>
            <input
                className="field__input"
                { ...inputParams }
            />
            <span className="field__errors" data-js-form-field-errors="">

            </span>
        </div>
    )
}

export default Field