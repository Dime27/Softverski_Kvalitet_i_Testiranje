export const url="http://localhost:8080/students/";

export const getAllStudents = () => {
    return fetch(url, {
        method:"GET",
        headers: {
            "Content-Type":"application/json"
        }
    })
};

export const updateStudent = (student) => {
    return fetch(url + student.index, {
        method:"PATCH",
        headers: {
            "Content-Type":"application/json"
        },
        body: JSON.stringify({
            index: student.index,
            name: student.name,
            lastName: student.lastName,
            studyProgram: {
                name: student.studyProgram.name
            }
        })
    })
};

export const addStudent = (student) => {
    return fetch(url, {
        method:"POST",
        headers: {
            "Content-Type":"application/json"
        },
        body: JSON.stringify({
            index: student.index,
            name: student.name,
            lastName: student.lastName,
            studyProgram: {
                name: student.studyProgram.name
            }
        })
    })
};

export const deleteStudent = (student) => {
    return fetch(url + student.index, {
        method:"DELETE",
        headers: {
            "Content-Type":"application/json"
        },
        body: JSON.stringify({
            index: student.index,
            name: student.name,
            lastName: student.lastName,
            studyProgram: {
                name: student.studyProgram.name
            }
        })
    })
};