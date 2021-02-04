import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CustomerService } from 'src/service/customer.service';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NotificationService } from 'src/service/notification.service';


@Component({
  selector: 'app-raise-ticket',
  templateUrl: './raise-ticket.component.html',
  styleUrls: ['./raise-ticket.component.css']
})
export class RaiseTicketComponent implements OnInit {

  public userFile: any = File;
  reactiveForms: any = FormGroup;
  error: string;

  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;


  constructor(private route: Router, private httpClient: HttpClient, private customerService: CustomerService, private fb: FormBuilder,private notifyService : NotificationService) { }

  ngOnInit(): void {
    this.reactiveForms = this.fb.group({
      title: new FormControl('', [Validators.required, Validators.compose([Validators.pattern('[a-zA-Z0-9 ]*'), Validators.minLength(3)])]),
      description: new FormControl('', [Validators.required, Validators.compose([Validators.pattern('[a-zA-Z ]*'), Validators.minLength(3)])])
    });
  }

  //Gets called when the user selects an image
  public onSelectFile(event) {

    const file = event.target.files[0];
    this.userFile = file;
  }


  //Gets called when the user clicks on submit to upload the image
  onUpload(submitForm: FormGroup) {

    if (submitForm.valid) {

      let customerId = sessionStorage.getItem('custId');
      const user = submitForm.value;
      const formData = new FormData();
      formData.append("user", JSON.stringify(user));
      formData.append("file", this.userFile);
      formData.append("customerId", customerId);
      this.message = "Ticket raised successfully";
      this.customerService.raiseTicket(formData).subscribe((response) => {
        console.log(response);
       this.showToasterSuccess();
        this.route.navigate(['/customer/dashboard/bugDetails/'])

      })

    } else {
      this.error = "Unable to raise ticket";
      this.showToasterFailure();
      setTimeout(() => {
        this.error = null;
      }, 5000);
    }
    submitForm.reset();
  }


  showToasterSuccess(){
    this.notifyService.showSuccess("Ticket Successfully Raised !!", "Raise Ticket");
} 

showToasterFailure(){
  this.notifyService.showError("Ticket Failed to Raised !!", "Raise Ticket");
} 

}
