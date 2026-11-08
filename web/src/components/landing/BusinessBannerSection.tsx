import React from 'react';

interface BusinessBannerSectionProps {
  onContactSales?: () => void;
}

export const BusinessBannerSection: React.FC<BusinessBannerSectionProps> = ({ onContactSales }) => {
  return (
    <section id="business" className="py-6 max-w-6xl mx-auto px-4 sm:px-6">
      <div className="rounded-3xl overflow-hidden bg-[#0A1224] text-white p-6 sm:p-8 border border-slate-800 shadow-xl">
        <div className="grid grid-cols-1 md:grid-cols-12 gap-6 items-center">
          
          {/* Team thumbnail on left */}
          <div className="md:col-span-3 hidden sm:block">
            <div className="rounded-2xl overflow-hidden">
              <img 
                src="/assets/business-team.jpg" 
                alt="Corporate Team" 
                className="w-full h-32 object-cover" 
              />
            </div>
          </div>

          {/* Center Info */}
          <div className="md:col-span-5 space-y-1.5">
            <div className="flex items-center gap-1.5 text-xs font-bold text-white">
              <span className="text-amber-400">👥</span>
              <span>Elevora for <span className="text-[#F5A623]">Businesses</span></span>
            </div>
            <p className="text-xs text-slate-300 leading-relaxed">
              Upskill your team's communication, collaboration and professional English with a structured program.
            </p>
          </div>

          {/* Right Checklist & Gold Button */}
          <div className="md:col-span-4 space-y-3">
            <div className="space-y-1 text-[11px] text-slate-200">
              <div className="flex items-center gap-1.5">
                <span className="text-amber-400 font-bold">✓</span>
                <span>Job-ready communication skills</span>
              </div>
              <div className="flex items-center gap-1.5">
                <span className="text-amber-400 font-bold">✓</span>
                <span>Flexible and scalable learning</span>
              </div>
              <div className="flex items-center gap-1.5">
                <span className="text-amber-400 font-bold">✓</span>
                <span>Progress reports for teams</span>
              </div>
            </div>

            <button
              onClick={onContactSales || (() => window.location.href = "mailto:enterprise@elevora.sriddha.com")}
              className="w-full py-2.5 px-4 bg-[#F5A623] hover:bg-[#e0951b] text-slate-950 font-bold text-xs rounded-full flex items-center justify-center gap-1.5 shadow-md transition-all"
            >
              <span>Know More for Teams</span>
              <span className="text-sm font-black">→</span>
            </button>
          </div>

        </div>
      </div>
    </section>
  );
};
