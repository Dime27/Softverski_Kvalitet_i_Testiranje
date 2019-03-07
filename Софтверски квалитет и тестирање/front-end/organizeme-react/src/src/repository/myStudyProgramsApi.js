export const url="http://localhost:8080/study_programs/";

export const getAllStudyPrograms = () => {
    return fetch(url, {
        method:"GET",
        headers: {
            "Content-Type":"application/json"
        }
    })
};

export const addStudyProgram = (studyProgram) => {
    return fetch(url, {
        method:"POST",
        headers: {
            "Content-Type":"application/json"
        },
        body: JSON.stringify({
            name: studyProgram.name
        })
    })
};

export const updateStudyProgram = (studyProgram) => {
    return fetch(url + studyProgram.id, {
        method:"PATCH",
        headers: {
            "Content-Type":"application/json"
        },
        body: JSON.stringify({
            id: studyProgram.id,
            name: studyProgram.name,
        })
    })
};

export const deleteStudyProgram = (studyProgram) => {
    return fetch(url + studyProgram.id, {
        method:"DELETE",
        headers: {
            "Content-Type":"application/json"
        }
    })
};