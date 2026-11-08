import React from 'react';
import { ElevoraLogo } from './ElevoraLogo';

interface AppPhoneMockupProps {
  onInteract?: () => void;
}

export const AppPhoneMockup: React.FC<AppPhoneMockupProps> = ({ onInteract }) => {
  return (
    <div className="relative mx-auto w-[275px] sm:w-[295px] select-none">
      {/* Outer Phone Bezel - Sleek Silver/Titanium border as shown in mockup */}
      <div className="relative rounded-[46px] p-[9px] bg-gradient-to-b from-slate-400 via-slate-600 to-slate-800 shadow-[0_25px_60px_-15px_rgba(0,0,0,0.8)] border border-slate-500/40">
        
        {/* Dynamic Island Notch */}
        <div className="absolute top-3.5 left-1/2 -translate-x-1/2 w-24 h-4 bg-black rounded-full z-30 flex items-center justify-center">
          <div className="w-2.5 h-2.5 bg-slate-900 rounded-full border border-slate-800 ml-8"></div>
        </div>

        {/* Inner Screen */}
        <div className="relative rounded-[38px] overflow-hidden bg-[#0A1020] text-white pt-7 pb-3 px-3.5 flex flex-col justify-between border border-slate-800 min-h-[530px]">
          
          <div className="space-y-3.5">
            {/* Logo inside App Screen */}
            <div className="flex items-center justify-center pt-1">
              <ElevoraLogo size="sm" />
            </div>

            {/* User Greeting Card */}
            <div className="flex items-center gap-2.5 bg-[#10192D] p-2.5 rounded-2xl border border-slate-800/80">
              <div className="w-8 h-8 rounded-full overflow-hidden bg-slate-700 shrink-0 border border-amber-400/40">
                <img 
                  src="/assets/why-choose-collage.jpg" 
                  alt="Raju" 
                  className="w-full h-full object-cover object-top" 
                />
              </div>
              <div className="flex-1 min-w-0">
                <div className="text-[11px] font-bold text-white flex items-center gap-1">
                  Hi Raju <span className="text-amber-400 text-xs">👋</span>
                </div>
                <div className="text-[9px] text-slate-400 truncate">
                  Let's continue your learning
                </div>
              </div>
            </div>

            {/* Course Card: Module 1 Managerial English Foundations */}
            <div className="bg-[#121E36] p-3.5 rounded-2xl border border-amber-500/20 shadow-lg relative overflow-hidden">
              <div className="flex items-start justify-between">
                <div>
                  <span className="text-[9px] font-bold text-slate-400 uppercase tracking-wider block">
                    Module 1
                  </span>
                  <h4 className="text-xs font-bold text-white leading-tight mt-0.5">
                    Managerial English Foundations
                  </h4>
                  <span className="text-[9px] text-slate-400 mt-0.5 block">
                    Week 1 of 4
                  </span>
                </div>
                {/* 0% progress circular dial */}
                <div className="w-8 h-8 rounded-full border-2 border-slate-700 border-t-amber-400 flex items-center justify-center text-[8px] font-bold text-amber-400">
                  0%
                </div>
              </div>

              {/* Graphic illustration placeholder */}
              <div className="my-2.5 h-16 bg-[#0B1323] rounded-xl flex items-center justify-center overflow-hidden border border-slate-800">
                <svg className="w-20 h-12 text-amber-400/80" viewBox="0 0 100 60" fill="none">
                  {/* Stepping figure graphic */}
                  <path d="M20 50 L40 50 L40 38 L60 38 L60 26 L80 26" stroke="#4A5568" strokeWidth="3" />
                  <circle cx="50" cy="20" r="5" fill="#F5A623" />
                  <path d="M50 25 L50 36 L43 45 M50 36 L57 45" stroke="#F5A623" strokeWidth="2.5" strokeLinecap="round" />
                </svg>
              </div>

              {/* Continue Button */}
              <button
                onClick={onInteract}
                className="w-full py-2 bg-[#F5A623] hover:bg-[#e0951b] text-slate-950 font-bold text-[10px] rounded-xl flex items-center justify-center gap-1.5 shadow-md transition-transform active:scale-98"
              >
                <span className="w-3.5 h-3.5 rounded-full bg-slate-950 text-[#F5A623] flex items-center justify-center text-[8px]">▶</span>
                <span>Continue</span>
              </button>
              <div className="text-[8px] text-center text-slate-400 mt-1">
                Day 1 - Introduction & Goals
              </div>
            </div>

            {/* Quick 4 Icon Row (AI Tutor, Practice, Progress, Notes) */}
            <div className="grid grid-cols-4 gap-1.5 text-center">
              <div className="bg-[#10192D] p-1.5 rounded-xl border border-slate-800">
                <div className="text-amber-400 text-xs">🎙️</div>
                <div className="text-[8px] text-slate-300 mt-0.5">AI Tutor</div>
              </div>
              <div className="bg-[#10192D] p-1.5 rounded-xl border border-slate-800">
                <div className="text-amber-400 text-xs">📝</div>
                <div className="text-[8px] text-slate-300 mt-0.5">Practice</div>
              </div>
              <div className="bg-[#10192D] p-1.5 rounded-xl border border-slate-800">
                <div className="text-amber-400 text-xs">📊</div>
                <div className="text-[8px] text-slate-300 mt-0.5">Progress</div>
              </div>
              <div className="bg-[#10192D] p-1.5 rounded-xl border border-slate-800">
                <div className="text-amber-400 text-xs">📋</div>
                <div className="text-[8px] text-slate-300 mt-0.5">Notes</div>
              </div>
            </div>

          </div>

          {/* Bottom App Navigation Bar */}
          <div className="pt-2 border-t border-slate-800 flex items-center justify-around text-slate-500">
            <div className="flex flex-col items-center text-amber-400">
              <span className="text-xs">🏠</span>
              <span className="text-[8px] font-bold">Home</span>
            </div>
            <div className="flex flex-col items-center">
              <span className="text-xs">📚</span>
              <span className="text-[8px]">Courses</span>
            </div>
            <div className="flex flex-col items-center">
              <span className="text-xs">🤖</span>
              <span className="text-[8px]">AI Tutor</span>
            </div>
            <div className="flex flex-col items-center">
              <span className="text-xs">👤</span>
              <span className="text-[8px]">Profile</span>
            </div>
          </div>

        </div>
      </div>
    </div>
  );
};
