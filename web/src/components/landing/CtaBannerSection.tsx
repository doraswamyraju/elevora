import React from 'react';

interface CtaBannerSectionProps {
  onStartLearning: () => void;
  onWatchDemo: () => void;
}

export const CtaBannerSection: React.FC<CtaBannerSectionProps> = ({ onStartLearning, onWatchDemo }) => {
  return (
    <section className="py-14 bg-[#081124] text-white">
      <div className="max-w-6xl mx-auto px-4 sm:px-6">
        <div className="flex flex-col md:flex-row items-center justify-between gap-6 text-center md:text-left">
          
          <div className="space-y-1.5">
            <h2 className="text-2xl sm:text-3xl font-extrabold text-white font-display">
              Ready to Speak English <br className="hidden sm:inline" />
              <span className="relative inline-block text-[#F5A623]">
                with Confidence?
                <svg className="absolute -bottom-1.5 left-0 w-full h-2.5 text-[#F5A623]" viewBox="0 0 170 10" fill="none">
                  <path d="M2.5 7C45 2 125 1 167.5 7.5" stroke="#F5A623" strokeWidth="3" strokeLinecap="round" />
                </svg>
              </span>
            </h2>
            <p className="text-xs text-slate-300">
              Join Elevora today and build the workplace English skills you need for your career growth.
            </p>
          </div>

          <div className="flex flex-wrap items-center justify-center gap-3 shrink-0">
            <button
              onClick={onStartLearning}
              className="px-6 py-3 rounded-full text-xs font-bold text-slate-950 bg-[#F5A623] hover:bg-[#e0951b] shadow-md shadow-amber-500/20 flex items-center gap-1.5 transition-all"
            >
              <span>Get Started Now</span>
              <span className="text-sm font-black">→</span>
            </button>

            <button
              onClick={onWatchDemo}
              className="px-5 py-3 rounded-full text-xs font-semibold text-white bg-[#0A1224] hover:bg-[#121E36] border border-slate-700 flex items-center gap-1.5 transition-all"
            >
              <span className="w-4 h-4 rounded-full border border-slate-500 flex items-center justify-center text-[8px] text-amber-400">▶</span>
              <span>Watch Demo</span>
            </button>
          </div>

        </div>
      </div>
    </section>
  );
};
