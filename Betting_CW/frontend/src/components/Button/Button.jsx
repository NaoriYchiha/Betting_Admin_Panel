import './Button.scss'
import classNames from "classnames";

const Button = (props) => {
    const {
        className,
        label,
        href,
        target,
        type = 'button',
        /**
         *  '' (default) | 'logReg'
         */
        mode = '',
        dataAtr,
    } = props

    const isLink = href !== undefined
    const Component = isLink ? 'a' : 'button'
    const linkProps = { href, target }
    const buttonProps = { type }
    const specificProps = isLink ? linkProps : buttonProps

    return (
        <Component
            className={classNames(className, 'button', {
                [`button--${mode}`]: mode,
            })}
            {...specificProps}
            data-js-form-button={dataAtr}
        >
            {label}
        </Component>
    )
}

export default Button