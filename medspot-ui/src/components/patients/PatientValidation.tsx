function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

const validateFields = (patient) => {
    const errors = {}

    const nameRegex = /^[A-Za-z-']*$/;
    const emailRegex = /^[a-zA-Z0-9]+@[a-zA-Z]+?\.[a-zA-Z]+$/;
    const ssnRegex = /^([0-9]){3}-([0-9]){2}-([0-9]){4}$/;
    const postalRegex = /^[0-9]{5}(?:-[0-9]{4})?$/;

    if(patient.age && patient.age <= 0) {
        errors.age = "Age must be greater than 0"
    }
    if(patient.weight && patient.weight <= 0) {
        errors.weight = "Weight must be greater than 0."
    }
    if(patient.height && patient.height <= 0) {
        errors.height = "Height must be greater than 0"
    }

    if(patient.firstName && !patient.firstName.match(nameRegex)){
        errors.firstName = "First Name must be alphabetic and can consist of hyphens and apostrophe's."
    }

    if(patient.lastName && !patient.lastName.match(nameRegex)){
        errors.lastName = "Last Name must be alphabetic and can consist of hyphens and apostrophe's."
    }

    if(patient.email && !patient.email.match(emailRegex)){
        errors.email = "Email must be a valid email format."
    }

    if(patient.ssn && !patient.ssn.match(ssnRegex)){
        errors.ssn = "SSN must match the format DDD-DD-DDDD where D is any numerical digit."
    }

    if(patient.postal && !patient.postal.match(postalRegex)){
        errors.postal = "Postal must be in the format DDDDD or DDDDD-DDDD where D is any numerical digit."
    }

    console.log(patient, errors)

    return errors;

}

export const validateRequiredFields = (patient) => {
    let errors = {};
    Object.keys(patient).forEach((field) => {
        if(!patient[field]) errors[field] = `${capitalizeFirstLetter(field)} is required.`;
    })

    errors = {
        ...errors,
        ...validateFields(patient)
    };
    return errors;
}


