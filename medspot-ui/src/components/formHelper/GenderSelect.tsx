import './FormHelper.css'

const GenderSelect = (props) => {
    const { onChange, errorMessage, defaultVal } = props;
    return (
        <div>
             <label>State</label>
            <select
            style={{ border: errorMessage ? '1px solid red' : '1px solid black'  }}
            name="gender"
            id="gender"
            defaultValue={defaultVal}
            onChange={onChange}>
                <option value="" selected disabled>Select Gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select>
            { errorMessage ? 
    (
        <span className='error'>{errorMessage}</span>
    ) :
    (
        <></>
    ) 
    }
        </div>
    )
}

export default GenderSelect;