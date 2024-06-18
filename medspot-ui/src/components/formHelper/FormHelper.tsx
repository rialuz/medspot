import './FormHelper.css'

const FormHelper = (props) => {
    const { id, defaultVal, type, label, name, placeholder, errorMessage, onChange } = props;


    return (
    <div className="formInput">
      <label>{label}</label>
      <input
      defaultValue={defaultVal}
      type={type}
      name={name}
      key={id}
      placeholder={placeholder}
      onChange={onChange}
      step=".01"
      style={{ border: errorMessage ? '1px solid red' : '1px solid black'  }}
      />

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

export default FormHelper;