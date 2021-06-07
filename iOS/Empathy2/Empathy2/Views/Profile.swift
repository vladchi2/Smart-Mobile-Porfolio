//
//  Profile.swift
//  Empathy2
//
//  Created by Vlad on 15/04/2021.
//

import SwiftUI

struct Profile: View {
    
    @State var username = "BBno$"
    @State var bio = "I had some problems, but not anymore. I want to help people sharing my experience"
    @State var age = 30
    @State var gender = "Male"

    var body: some View {
       
        NavigationView{
            ZStack{
                LinearGradient(gradient: /*@START_MENU_TOKEN@*/Gradient(colors: [Color.red, Color.blue])/*@END_MENU_TOKEN@*/, startPoint: /*@START_MENU_TOKEN@*/.leading/*@END_MENU_TOKEN@*/, endPoint: /*@START_MENU_TOKEN@*/.trailing/*@END_MENU_TOKEN@*/)
                
                
                VStack(){
                    
                    Text("Billy Boy")
                        .font(.largeTitle)
                    
                    Image("user1")
                        .resizable()
                        .clipShape(Circle())
                        .frame(width: 150 , height: 150)
                        .clipped()
                    
                    Form{
                        
                        Section(header: Text("USERNAME")) {
                            Text("BBno$")
                            //TextField("Your title", text: $username)
                        }
                        
                        
                        Section(header: Text("BIO")) {
                            Text("I had some problems, but not anymore. I want to help people sharing my experience")
                            //TextField("Your BIO", text: $bio)
                             //   .frame(width: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/, height: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                          
                        }
                        Section(header: Text("AGE")) {
                            Text("30 years old")
                            //TextField("Your title", text: $username)
                        }
                        Section(header: Text("GENDER")) {
                            Text("Male")
                            //TextField("Your title", text: $username)
                        }
                        Section(header: Text("STATUS")) {
                            Text("Single")
                            //TextField("Your title", text: $username)
                        }
                    }
                    
                    
                    
                    
                }
            }
            .navigationTitle("Profile")
        }
                   
          
    }
}

struct Profile_Previews: PreviewProvider {
    static var previews: some View {
        Profile()
            .preferredColorScheme(.dark)
            
    }
}
