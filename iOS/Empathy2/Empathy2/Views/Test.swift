//
//  Test.swift
//  Empathy2
//
//  Created by Vlad on 14/04/2021.
//

import SwiftUI

struct Test: View {
    init()
    {
        UITabBar.appearance().barTintColor = .systemBackground
    }
    var icons = ["person","person", "plus.app","person","gear",]
    
    @State var showModal = false
    @State var selectedIndex = 0
    
    var body: some View
    {
        VStack
        {
            ZStack
            {
                Spacer()
                    .fullScreenCover(isPresented: $showModal, content: {
                        Button(action: {showModal.toggle()}, label: {
                            /*@START_MENU_TOKEN@*/Text("Button")/*@END_MENU_TOKEN@*/
                        })
                        Text("Wassup")
                    })
                
                switch selectedIndex
                {
                case 0: PostView()
                case 1: ContentView()
                case 2: AddPost()
                case 3: PostView()
                    
                default:
                    Text("Remaining")
                }
            }
            
            Spacer()
 
            HStack
            {
                ForEach(0..<5) {num in
                    Button(action: {
                        
                        selectedIndex = num
                        
                        if num == 2
                        {
                        showModal.toggle()
                        }
                        
                    }, label: {
                        Spacer()
                        
                        if num == 2
                        {
                            Image(systemName: icons[num])
                                .font(.system(size:49,weight: .light))
                                .foregroundColor(.blue)
                        }
                        else
                        {
                        Image(systemName: icons[num])
                            .font(.system(size:26,weight: .bold))
                            .foregroundColor( selectedIndex == num ? Color(.label) :
                                                .init(white:0.8))
                        }
                        Spacer()
                        
                    })
                    
                  
                }
               
            }
        }
    }
}
    
        





struct Test_Previews: PreviewProvider {
    static var previews: some View {
        Test()
    }
}
