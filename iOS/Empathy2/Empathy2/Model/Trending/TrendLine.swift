//
//  TrendLine.swift
//  Empathy2
//
//  Created by Mihail on 12/04/2021.
//

import SwiftUI

struct TrendLine:View{
    var body: some View{
      
            ScrollView(.horizontal)
            {
                VStack(alignment: .leading)
                {
                    Text("Trending").font(.headline).bold()
                    HStack
                    {
                        VStack{
                            Image("ciggy").renderingMode(.original).resizable().frame(width: 100, height: 100)
                            Text("Ciggarettes").foregroundColor(.primary)
                        }
                        VStack{
                            Image("drink").renderingMode(.original).resizable().frame(width: 100, height: 100)
                            Text("Alchocol")
                        }
                        VStack{
                            Image("unemployed").renderingMode(.original).resizable().frame(width: 100, height: 100)
                            Text("Unemployment").colorMultiply(.primary)
                        }
                        VStack{
                            Image("violence").renderingMode(.original).resizable().frame(width: 100, height: 100)
                            Text("Violence")
                        }
                        VStack{
                            Image("unemployed").renderingMode(.original).resizable().frame(width: 100, height: 100)
                            Text("Unemployment")
                        }
                        VStack{
                            Image("ciggy").renderingMode(.original).resizable().frame(width: 100, height: 100)
                            Text("Ciggarettes")
                        }
                    }
                }
                
            }.frame(height:170)
        }
    }



