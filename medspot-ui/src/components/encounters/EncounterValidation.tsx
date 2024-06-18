
const validateFields = (encounter) => {
    const errors= {};

    const { visitCode, billingCode, icd10, date, totalCost, copay, pulse, systolic, diastolic } = encounter;

    const visitCodeRegex = /^([A-Z]){1}([0-9]){1}([A-Z]){1} ([0-9]){1}([A-Z]){1}([0-9]){1}$/;
    const billingCodeRegex = /^([0-9]){3}.([0-9]){3}.([0-9]){3}-([0-9]){2}$/;
    const icd10Regex = /^([A-Z]){1}([0-9]){2}$/;
    const dateRegex = /^\d{4}-\d{2}-\d{2}$/;

    if(!totalCost || totalCost < 0) errors.totalCost = "Must be greater than 0."
    if(!copay || copay < 0) errors.copay = "Must be greater than 0."
    if(pulse && pulse < 0) errors.pulse = "Must be greater than 0."
    if(systolic && systolic < 0) errors.systolic = "Must be greater than 0."
    if(diastolic && diastolic < 0) errors.diastolic = "Must be greater than 0."

    if(visitCode && !visitCode.match(visitCodeRegex)) errors.visitCode = "Visit code must match the format LDL DLD where L is any capital alphabetical character and D is any numerical digit.";
    if(billingCode && !billingCode.match(billingCodeRegex)) errors.billingCode = "Billing code must match the format DDD.DDD.DDD-DD where D is any numerical digit.";
    if(icd10 && !icd10.match(icd10Regex)) errors.icd10 = "ICD10 must match the format LDD where L is any capital letter and D is any numerical digit.";
    if(date && !date.match(dateRegex)) errors.date = "Date must be in a valid YYYY-MM-DD date format.";

    return errors;
}

export const validateRequiredEncounterFields = (encounter) => {
    let errors = {};

    if(!encounter.visitCode) errors.visitCode = "Visit Code is required."
    if(!encounter.provider) errors.provider = "Provider is required."
    if(!encounter.billingCode) errors.billingCode = "Billing Code is required."
    if(!encounter.icd10) errors.icd10 = "ICD10 is required."
    if(!encounter.chiefComplaint) errors.chiefComplaint = "Chief Complaint is required."
    if(!encounter.date) errors.date = "Date is required."

    errors = {
        ...errors,
        ...validateFields(encounter)
    };
    return errors;
}