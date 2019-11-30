// IStudentService.aidl
package com.hyy.app.remote;
import com.hyy.app.remote.Student;
interface IStudentService {

	Student getStudentById(int id);
}
