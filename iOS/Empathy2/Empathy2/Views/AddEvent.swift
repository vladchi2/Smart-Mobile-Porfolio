//
//  PostPageTest.swift
//  Empathy2
//
//  Created by Vlad on 14/04/2021.
//

import SwiftUI

struct PostPageTest: View {
    @State var receive = false
    @State var number = 1
    @State var selection = 2
    @State var date = Date()
    @State var title = ""
    @State var description = ""
    @State var submit = false
    
    var body: some View {
        
            
       
        
        NavigationView {
            VStack {
                Form {
                    Toggle(isOn: $receive, label: {
                        Text("Recieve notification")
                    })
                    Stepper(value: $number, in: 1...10) {
                        Text("\(number) Person\(number > 1 ? "s" : "") event")
                    }
                    
                    DatePicker(selection: $date) {
                        Text("Date")
                    }
                    
                    Section(header: Text("Choose event topic")) {
                        Picker(selection: $selection, label: Text("Topic")) {
                            Text("Workshop").tag(1)
                            Text("Board games night").tag(2)
                            Text("Alchohol").tag(3)
                            
                        }
                    }
                    Section(header: Text("Location")) {
                        TextField("Address, City, Country", text: $title)
                    }
                    
                    Section(header: Text("Description")) {
                        TextField("Event description", text: $description)
                            .frame(height: 150, alignment: .topLeading)
                    }
                    
                    
                
                    
                }
                Button(action: {submit.toggle() }) {
                    Text("Submit")
                        .frame(width:250,height: 50, alignment: .center)
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }
                Spacer()
            }
                .navigationBarTitle("Post an event")
            
            
        }

}
}

struct PostPageTest_Previews: PreviewProvider {
    static var previews: some View {
        PostPageTest()
            .preferredColorScheme(.dark)
    }
}
