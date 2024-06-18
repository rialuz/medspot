import './FormHelper.css'

const FormHelperTextArea = (props) => {
    const { id, label, value,  name, onChange, defaultVal, placeholder, errorMessage } = props
    return (
        <div className='formInput'>
            <label>{label}</label>
            <textarea
            defaultValue={defaultVal}
            key={id}
            name={name}
            onChange={onChange}
            placeholder={placeholder}
            cols={20}
            rows={2}
            style={{ border: errorMessage ? '1px solid red' : '1px solid black'  }}
            >
            </textarea>
            { errorMessage ? 
    (
        <span className="error">{errorMessage}</span>
    ) :
    (
        <></>
    ) 
    }
        </div>
    )
}

export default FormHelperTextArea;