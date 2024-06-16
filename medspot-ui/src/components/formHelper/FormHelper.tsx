import { useState } from "react";
import './FormHelper.css'

const FormHelper = (props) => {
    const [focused, setFocused] = useState(false);
    const { id, defaultVal, type, label, name, placeholder, errorMessage, onChange } = props;

    const handleFocus = (e:any) => {
        setFocused(true);
      };

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
      onBlur={handleFocus}
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